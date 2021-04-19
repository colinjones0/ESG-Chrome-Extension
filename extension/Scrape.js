//document.getElementById("score").addEventListener("click", Scrape);
window.addEventListener("load", Scrape);

function myFunction() {
    console.log('hi');
}
/**
 * This class handles web scraping
 * @returns {JSX.Element}
 * @constructor
 */
function Scrape() {

        /* gets the url of the page we are on*/

    let currPage = null;

    function getUrl() {
        return new Promise(resolve => {
            chrome.tabs.query({active: true, currentWindow: true}, tabs => {
                currPage = tabs[0].url;
                console.log(currPage);
                resolve(currPage);
        })
    })
    }

    async function asyncRequest() {
        const newPage = await getUrl();
        axios.post(
            "http://localhost:4567/findCompany",
            {
                currPage: newPage,
            },
            {
                headers: {
                    "Content-Type": "application/json",
                    'Access-Control-Allow-Origin': '*',
                }
            }
        )
            .then((response) => {
                // console.log(response.data["recommendations"])
                // console.log(response.data["recommendations"][3][4])
                // if (response.data["recommendations"][0][0] === "error"){
                //     document.getElementById("loading-text").innerHTML = "no data found for this company"
                //     document.getElementById("loader").style.display = "none";
                // }
                // else {
                    document.getElementById("load-background").style.display = "none";
                    document.getElementById("score").innerHTML = response.data["recommendations"][3][4];
                    document.getElementById("recommendation-1-website").innerHTML = response.data["recommendations"][0][0];
                    document.getElementById("recommendation-1-website").href = response.data["recommendations"][0][2];
                    document.getElementById("recommendation-2-website").innerHTML = response.data["recommendations"][1][0];
                    document.getElementById("recommendation-2-website").href = response.data["recommendations"][1][2];
                    document.getElementById("recommendation-3-website").innerHTML = response.data["recommendations"][2][0];
                    document.getElementById("recommendation-3-website").href = response.data["recommendations"][2][2];
                    document.getElementById("environment-article").href = response.data["recommendations"][3][5];
                    document.getElementById("social-article").href = response.data["recommendations"][3][6];
                    document.getElementById("governance-article").href = response.data["recommendations"][3][7];
                    document.getElementById("recommendation-1-image").src = response.data["recommendations"][0][8];
                    document.getElementById("recommendation-2-image").src = response.data["recommendations"][1][8];
                    document.getElementById("recommendation-3-image").src = response.data["recommendations"][2][8];
                    document.getElementById("recommendation-1-score").innerHTML = "ESG: " + response.data["recommendations"][0][4];
                    document.getElementById("recommendation-2-score").innerHTML = "ESG: " + response.data["recommendations"][1][4];
                    document.getElementById("recommendation-3-score").innerHTML = "ESG: " + response.data["recommendations"][2][4];

                //}
                return response.data["recommendations"]
            })
            .catch((error) => {
                if (error.response) {
                    // The request was made and the server responded with a status code
                    // that falls out of the range of 2xx
                    console.log(error.response.data);
                    console.log(error.response.status);
                    console.log(error.response.headers);
                } else if (error.request) {
                    // The request was made but no response was received
                    // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
                    // http.ClientRequest in node.js
                    console.log(error.request);
                } else {
                    // Something happened in setting up the request that triggered an Error
                    console.log('Error', error.message);
                }
        })

    }
    asyncRequest();
}
