document.getElementById("score").addEventListener("click", Scrape);

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
    async function getUrl() {
        let promise = new Promise((res, rej) => {
            chrome.tabs.query({active: true, currentWindow: true}, tabs => {
                currPage = tabs[0].url;
                console.log(currPage);
                console.log("inside query");
            });
            //return currPage;
        });
    }
    getUrl().then(

    axios.post(
            "http://localhost:4567/findCompany",
            {
                currPage: currPage,
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
                return response.data["recommendations"]
    }));
}