let report;
let startDate;
let endDate;
let Chart = require('chart.js');

function setDatesAndTimes()
{
  startDate = document.getElementById('startDate').value.toString();
  endDate = document.getElementById('endDate').value.toString();
  requestAndChart();
}

function showOrHide(div) {
  if (div.style.display === "none") {
    div.style.display = "block";
  } else {
    div.style.display = "none";
  }
}

const requestAndChart = () => {
  fetch('/views?startDate='+startDate+'&endDate='+endDate)
      .then((response) => {
        if (!(response.statusText==="OK")) {
          throw Error(response.statusText);
        }
        return response.json();
      })
      .then((json) => {
        report = json;
    })
    .finally(async () => {
      /** @namespace report.numRequestsPerMin[] **/
      /** @namespace report.numUniques **/
      /** @namespace report.mostViewedURLs[] **/
      /** @namespace report.mostViewedURLs[] **/
      let labels = [];
      let minutes;
      let startDateObject = new Date(startDate);
      let labelsSize = report.numRequestsPerMin.length;
      for (let i = 0; i<labelsSize;i++)
      {
        labels[i]=new Date(startDateObject.getTime() + i*60000);
      }
      let ctx = document.getElementById('canvas').getContext('2d');
      new Chart(ctx,{"type":"line","data":{"labels":labels,"datasets":[{"label":"Users Per Minute","data":report.numRequestsPerMin,"fill":false,"borderColor":"rgb(75, 192, 192)","lineTension":0.1}]},"options":{}});

  }).catch((error) => {
    console.log(error);

  })
  };