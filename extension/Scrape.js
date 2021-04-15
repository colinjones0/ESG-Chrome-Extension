import axios from 'axios';

document.getElementById("score").addEventListener("click", Scrape);
/**
 * This class handles web scraping
 * @returns {JSX.Element}
 * @constructor
 */
function Scrape() {
    //
    // /**
    //  * Gives the name of the company whose page we are on.
    //  */
    // const findCompanyRequest = () => {
        /* gets the url of the page we are on*/
        let currPage = window.location.href;

        //check if currPage is in database

        let response = axios.post(
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
        );
         console.log(response.data["recommendations"])
        return response.data["recommendations"];

    // }
}
