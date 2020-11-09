import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from "rxjs/Observable";
import { map } from "rxjs/operators";

import { Member } from "../models/member.model";
import { MemberSearch } from "../models/membersearch.model";
import { ServiceUtils } from "./serviceutils.service";



@Injectable()
export class MemberService {
  private apiUrl = "/reporting";
  private serviceUtils: ServiceUtils = new ServiceUtils();
  apiHeaders;

  /** Create constructor to get Http instance and service utils.*/
  constructor(private http: HttpClient) {
    this.apiHeaders = this.serviceUtils.buildApiHeaders();
}

  /**
     * Method to get member subscriber report data by member group and/or governmaent benefit type.
     * 
     * @param memberSearch 
     */
    public getMemberSubscribers(memberSearch: MemberSearch): Observable<Member[]> {
      console.log("Before Api CALL Request MemberGroupId: " + memberSearch.memberGroupId + ", Benefit Type: " + memberSearch.govtBenefitType);

      /** Consume API call. */
      return this.http.post<Member[]>('http://localhost:8086/reporting-data/member/subscribers', memberSearch, { headers: this.apiHeaders })
          .pipe(map(response => {
              return response;
          }));
  }

}
