import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from "rxjs/Observable";
import { map } from "rxjs/operators";

import { ClaimExpense} from "../models/claimsexpense.model";
import { ClaimExpenseSearch } from "../models/claimsexpensesearch.model";
import { ServiceUtils } from "../service/serviceutils.service";



@Injectable()
export class ClaimExpenseService {
  private apiUrl = "/reporting";
  private serviceUtils: ServiceUtils = new ServiceUtils();
  apiHeaders;

  /** Create constructor to get Http instance and service utils.*/
  constructor(private http: HttpClient) {
    this.apiHeaders = this.serviceUtils.buildApiHeaders();
}

  /**
     * Method to get claim expense report data by member group and service year.
     * 
     * @param claimExpenseSearch 
     */
    public getClaimsExpense(claimExpenseSearch: ClaimExpenseSearch): Observable<ClaimExpense[]> {
      /** Consume API call. */
      return this.http.post<ClaimExpense[]>('http://localhost:8086/reporting-data/claims/claimexpenses', claimExpenseSearch, { headers: this.apiHeaders })
          .pipe(map(response => {
              return response;
          }));
  }

}
