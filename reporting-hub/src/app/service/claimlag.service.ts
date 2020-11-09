import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from "rxjs/Observable";
import { map } from "rxjs/operators";

import { ClaimsLag } from "../models/claimslag.model";
import { ClaimLagSearch } from "../models/claimslagsearch.model";
import { ServiceUtils } from "../service/serviceutils.service";



@Injectable()
export class ClaimLagService {
  private apiUrl = "/reporting";
  private serviceUtils: ServiceUtils = new ServiceUtils();
  apiHeaders;

  /** Create constructor to get Http instance and service utils.*/
  constructor(private http: HttpClient) {
    this.apiHeaders = this.serviceUtils.buildApiHeaders();
}

  /**
     * Method to get claim lag report data by member group and book of year.
     * 
     * @param claimLagSearch 
     */
    public getClaimsLag(claimLagSearch: ClaimLagSearch): Observable<ClaimsLag[]> {

      //this.apiUrl + '/claims/claimlag'
      //http://localhost:8086/reporting-data/claims/claimlag

      /** Consume API call. */
      return this.http.post<ClaimsLag[]>('http://localhost:8086/reporting-data/claims/claimlag', claimLagSearch, { headers: this.apiHeaders })
          .pipe(map(response => {
              return response;
          }));
  }

}
