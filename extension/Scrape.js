//import React, {useEffect } from 'react';
import axios from 'axios';
/**
 * This class handles web scraping
 * @returns {JSX.Element}
 * @constructor
 */
function Scrape() {

    // let companyName;
    // /**
    //  * Gives the name of the company whose page we are on.
    //  */
    // const findCompanyRequest = () => {
    //     /* gets the url of the page we are on*/
    //     let currPage = window.location.href;
    //
    //     let response = axios.post(
    //         "http://localhost:4567/findCompany",
    //         {
    //             currPage : currPage,
    //         },
    //         {
    //             headers: {
    //                 "Content-Type": "application/json",
    //                 'Access-Control-Allow-Origin': '*',
    //             }
    //         }
    //     );
    //     return response.data["name"];
    // }
    //
    // /*
    // * On page load, create the initial canvas.
    // */
    // useEffect(() => {
    //     /* Set the ctxRef for future use, now that the page is loaded up. */
    //    companyName = findCompanyRequest;
    // }, [])
 }

//     return (
//         <div>hello</div>
//     );
// }
//
// export default Scrape;

function showAlert() {
   console.log('deez');
}