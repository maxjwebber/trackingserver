package edu.temple.trackingserver;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class TrackingTree
{
    TrackingItem trueRoot;

    public TrackingTree() { }

    public void add(TrackingItem newItem)
    {
        if (trueRoot==null)
        {
            trueRoot=newItem;
        }
        else
            add(newItem,trueRoot);
    }
    private void add(TrackingItem newItem,TrackingItem root)
    {
        if (newItem.getDate().isBefore(root.getDate())) {
            if (root.left == null)
            {
                root.left = newItem;
            }
            else
            {
                add(newItem, root.left);
            }
        }
        else
        {
            if (root.right == null)
            {
                root.right = newItem;
            }
            else
            {
                add(newItem, root.right);
            }
        }
    }

    public TrackingReport generateReport(LocalDateTime startDate,LocalDateTime endDate)
    {
        int[] numRequestsPerMin = new int[(int) Math.ceil(ChronoUnit.MINUTES.between(startDate, endDate))];
        Arrays.fill(numRequestsPerMin,0);
        TrackingItem current=trueRoot;
        Map<String,Integer> URLs = new HashMap<>();
        Map<String,Integer> browsers = new HashMap<>();
        ArrayList<Integer> uniqueIDs = new ArrayList<>();
        boolean rootNotFound = true;
        //find root for BFS
        while(rootNotFound)
        {
            if (current.getDate().isBefore(startDate))
                current=current.left;
            else if (current.getDate().isAfter(endDate))
                current=current.right;
            else
                rootNotFound=false;
        }
        //BFS starting from first valid root
        Queue<TrackingItem> q = new LinkedList<TrackingItem>();
        q.add(current);
        while (!q.isEmpty()) {
            current = q.remove();
            //validate
            if (!current.getDate().isAfter(endDate)&&!current.getDate().isBefore(startDate))
            {
                //Process current node.
                //1. add to uniqueIDs if the ID is unique
                if (!uniqueIDs.contains(current.getUserID()))
                    uniqueIDs.add(current.userID);
                //2. add URL or increment URL count
                if (URLs.containsKey(current.getThisURL()))
                    URLs.put(current.getThisURL(), URLs.get(current.getThisURL()) + 1);
                else
                    URLs.put(current.getThisURL(), 1);
                //3. add browser or increment browser count
                if (browsers.containsKey(current.getBrowser()))
                    browsers.put(current.getBrowser(), browsers.get(current.getBrowser()) + 1);
                else
                    browsers.put(current.getBrowser(), 1);
                //4. increment index of five minute timeframe in which request occurred
                numRequestsPerMin[(int) Math.ceil(ChronoUnit.MINUTES.between(startDate, current.getDate()))]++;
            }
            //add next nodes
            if (current.left != null)
                q.add(current.left);
            if (current.right != null)
                q.add(current.right);
        }
        return new TrackingReport(uniqueIDs.size(),getTopThree(URLs),getTopThree(browsers),numRequestsPerMin);
    }

    private String[] getTopThree(Map<String,Integer> map)
    {
        Map.Entry<String,Integer> firstPlace = null;
        Map.Entry<String,Integer> secondPlace= null;
        Map.Entry<String,Integer> thirdPlace = null;

        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if (firstPlace==null)
                firstPlace = entry;
            else if (firstPlace.getValue()<entry.getValue())
            {
                if (secondPlace!=null)
                {
                    thirdPlace = secondPlace;
                }
                secondPlace = firstPlace;
                firstPlace = entry;
            }
            else if (secondPlace==null)
            {
                secondPlace = entry;
            }
            else if (secondPlace.getValue()<entry.getValue())
            {
                thirdPlace = secondPlace;
                secondPlace = entry;
            }
            else if (thirdPlace==null||thirdPlace.getValue()<entry.getValue())
            {
                thirdPlace = entry;
            }
        }
        String[] output = new String[3];
        if (firstPlace!=null)
            output[0] = firstPlace.getKey();
        else
            output[0] = "";
        if (secondPlace!=null)
            output[1] = secondPlace.getKey();
        else
            output[1] = "";
        if (thirdPlace!=null)
            output[2] = thirdPlace.getKey();
        else
            output[2] = "";
        return output;
    }



}
