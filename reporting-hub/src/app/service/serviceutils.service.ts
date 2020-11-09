import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

import { Observable } from "rxjs";


export class ServiceUtils {
 
    /** Default constructor.*/
    constructor() { }

    /**
     * Method to handle api response.
     *
     * @param {Response | any} response
     */
    public handleApiResponse(response: Response | any) {
        console.log("Api Call Status :: " + response.text());
    }

    /**
     * Method to build api http headers.
     *
     * @returns {HttpHeaders}
     */
    public buildApiHeaders() {
        var headers = new HttpHeaders(
            {
                'content-type': 'application/json'
            }
        );

        return headers;
    }

    /** Method to handle any errors in API call. */
    public handleError(error: Response | any) {
        console.log('ApiService::handleError', error);
        console.error('ApiService::handleError', error);
        return Observable.throw(error);
    }

}