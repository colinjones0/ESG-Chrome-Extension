import axios from 'axios';
/**
 * This class handles web scraping
 * @returns {JSX.Element}
 * @constructor
 */
function Scrape() {

    /**
     * Gives the name of the company whose page we are on.
     */
    const findCompanyRequest = () => {
        /* gets the url of the page we are on*/
        let currPage = window.location.href;

        let response = axios.post(
            "http://localhost:4567/findCompany",
            {
                page : currPage,
            },
            {
                headers: {
                    "Content-Type": "application/json",
                    'Access-Control-Allow-Origin': '*',
                }
            }
        );
        return ;
    }

    return (
    <div className = "Scrape">

    </div>
    );
}

export default Scrape;