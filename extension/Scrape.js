//document.getElementById("score").addEventListener("click", Scrape);
document.addEventListener("load", Scrape);

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
                console.log(response.data["recommendations"])
                console.log(response.data["recommendations"][3][4])
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

                return response.data["recommendations"]
            });
    }
    asyncRequest();
}
