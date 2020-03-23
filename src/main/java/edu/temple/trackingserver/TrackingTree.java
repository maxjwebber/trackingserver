package edu.temple.trackingserver;

import java.time.LocalDateTime;

public class TrackingTree
{
    int count;
    int uniques;
    TrackingItem trueRoot;

    public TrackingTree()
    {
        count = 0;
        uniques = 0;
    }
    public void add(TrackingItem newItem)
    {
        count++;

        if (trueRoot==null)
        {
            trueRoot=newItem;
            uniques++;
        }
        else
            add(newItem,trueRoot);
    }
    private void add(TrackingItem newItem,TrackingItem root)
    {
        if (newItem.getUserID()<root.getUserID()) {
            if (root.left == null)
            {
                root.left = newItem;
                uniques++;
            }
            else
            {
                add(newItem, root.left);
            }
        }
        else if (newItem.getUserID()>root.getUserID()) {
            if (root.right == null)
            {
                root.right = newItem;
                uniques++;
            }
            else
            {
                add(newItem, root.right);
            }
        }
        else
        {
            if (root.middle == null)
            {
                root.middle = newItem;
            }
            else
            {
                add(newItem, root.middle);
            }
        }
    }




}
