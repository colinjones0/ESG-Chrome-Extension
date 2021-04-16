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
        console.log(newPage)
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
                return response.data["recommendations"]
            });
    }
    asyncRequest();
}