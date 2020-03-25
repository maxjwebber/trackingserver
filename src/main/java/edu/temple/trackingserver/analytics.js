let report;
let startDate;
let endDate;
let Chart = require('chart.js');
let ctx = document.getElementById('canvas').getContext('2d');

async function setDatesAndTimes()
{
    startDate = document.getElementById('startDate').value;
    endDate = document.getElementById('endDate').value;
    requestAndChart();
}
const requestAndChart = () => {
  fetch('/views?startDate='+startDate+'&endDate='+endDate)
      .then((response) => {
        if (!(response.statusText==="OK")) {
          throw Error(response.statusText);
        }
        return response();
      })
      .then((response) => {
        report = response;
      })
      .finally( () => {
        /** @namespace report.numRequestsPerMin[] **/
        /** @namespace report.numUniques **/
        /** @namespace report.mostViewedURLs[] **/
        /** @namespace report.mostUsedBrowsers[] **/
        let labels = [];

        let startDateObject = new Date(startDate);
        let labelsSize = report.numRequestsPerMin.length;
        for (let i = 0; i<labelsSize;i++)
        {
          labels[i]=new Date(startDateObject.getTime() + i*60000);
        }

        let fancyNewChart = new Chart(ctx,{"type":"line","data":{"labels":labels,"datasets":[{"label":"Users Per Minute","data":report.numRequestsPerMin,"fill":false,"borderColor":"rgb(75, 192, 192)","lineTension":0.1}]},"options":{}});
        document.getElementById("uniques").innerHTML="There are "+report.numUniques+" unique User IDs for this period.";
        document.getElementById("topBrowsers").innerHTML="The top browsers are:\n1) "+report.mostUsedBrowsers[0]+"\n2) "+report.mostUsedBrowsers[1]+"\n3) "+report.mostUsedBrowsers[2];
        document.getElementById("topSites").innerHTML="The top sites are:\n1) "+report.mostViewedURLs[0]+"\n2) "+report.mostViewedURLs[1]+"\n3) "+report.mostViewedURLs[2];
      }).catch((error) => {
      console.log(error);

  })
};



