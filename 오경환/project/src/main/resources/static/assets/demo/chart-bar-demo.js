// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

console.log(labelList);
// Bar Chart Example
let ctx = document.getElementById("myBarChart");
let myLineChart = new Chart(ctx, {
  type: 'bar',
  data: {
    labels: labelList,
    datasets: [{
      label: "월별 가입 회원수",
      backgroundColor: "rgba(2,117,216,1)",
      borderColor: "rgba(2,117,216,1)",
      data: valueList,
    }],
  },
  options: {
    scales: {
      xAxes: [{
        time: {
          unit: '월'
        },
        gridLines: {
          display: false
        },
        ticks: {
          maxTicksLimit: 6
        }
      }],
      yAxes: [{
        ticks: {
          min: 0,
          max: 10,
          maxTicksLimit: 1
        },
        gridLines: {
          display: true
        }
      }],
    },
    legend: {
      display: false
    }
  }
});
