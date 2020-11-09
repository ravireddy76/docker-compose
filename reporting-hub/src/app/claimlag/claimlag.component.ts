import { Component, OnInit } from '@angular/core';
import { ClaimLagService } from "../service/claimlag.service";
import { Observable } from "rxjs/Observable";
import { Router } from "@angular/router";
import { jsPDF } from 'jspdf';
import autoTable from 'jspdf-autotable'
import * as XLSX from 'xlsx'; 

import html2canvas from 'html2canvas'; 

import { ClaimsLag } from '../models/claimslag.model';
import { ClaimLagSearch } from '../models/claimslagsearch.model';

@Component({
    selector: 'app-claimlag',
    templateUrl: './claimlag.component.html',
    styleUrls: ['./claimlag.component.css'],
    providers: [ClaimLagService]
})
export class ClaimLagComponent implements OnInit {
    public claimsLagSearch: ClaimLagSearch = new ClaimLagSearch();
    claimsLagReportData: Observable<ClaimsLag[]>;

    constructor(private router: Router, private claimLagService: ClaimLagService) { }

    ngOnInit() { }

    /** Method to generate claim lag report by member group id and book of year. */
    public generateClaimLagReport() {
        console.log("Api Request MemberGroupId: " + this.claimsLagSearch.memberGroupId + ", Book Of Year: " + this.claimsLagSearch.bookYear);

        /** Trigger service api call. **/
        this.claimsLagReportData = this.claimLagService.getClaimsLag(this.claimsLagSearch);

        //TODO Future code approach..
        // this.claimLagService.getClaimsLag(this.claimsLagSearch).subscribe((searchResults) => {
        //     this.claimsLagReportData = ClaimLagComponent.mapToRuleResults(searchResults);
        // });
    }


    public exportPDF() {
        var data = document.getElementById('htmlReportData');  
        html2canvas(data).then(canvas => {  
            // Few necessary setting options  
            var imgWidth = 208;   
            var pageHeight = 295;    
            var imgHeight = canvas.height * imgWidth / canvas.width;  
            var heightLeft = imgHeight;  
            var pdfFileName = 'MemberGroupId_'+this.claimsLagSearch.memberGroupId+'-BookYear_'+this.claimsLagSearch.bookYear+".pdf";
  
            const contentDataURL = canvas.toDataURL('image/png')  
            let pdf = new jsPDF('p', 'mm', 'a4'); // A4 size page of PDF  
            var position = 12;  

            /* Header and Footer information. */ 
            var headerInformation = 'Claim Lag Report For ~ Member Group Id : '+this.claimsLagSearch.memberGroupId+', Book Of Year : '+this.claimsLagSearch.bookYear;
            pdf.setFont("helvetica");
            pdf.setFontSize(9);  
            pdf.setTextColor("#800000");
            pdf.text(headerInformation, 0, 8);
            pdf.text("Copyrights to E&A team @2020",10, 250);

            /* Page content information. */
            pdf.addImage(contentDataURL, 'PNG', 0, position, imgWidth, imgHeight);
            pdf.save(pdfFileName); // Generated PDF   
        });  
    }

    public exportCSV(): void {
        //TODO Coming in future
    }

    public exportExcel(): void {
        var fileName = 'MemberGroupId_'+this.claimsLagSearch.memberGroupId+'-BookYear_'+this.claimsLagSearch.bookYear+".xlsx";
        
        /* table id is passed over here */   
        let data = document.getElementById('htmlReportData');  
        const ws: XLSX.WorkSheet =XLSX.utils.table_to_sheet(data);

        /* generate workbook and add the worksheet */
        const wb: XLSX.WorkBook = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');

        /* save to file */
        XLSX.writeFile(wb, fileName);
    }

    static mapToRuleResults(response: Array<Object>): ClaimsLag[] {
        return response.map((data) => ClaimLagComponent.buildRuleResult(data));
    }

    static buildRuleResult(r: any): ClaimsLag {
        let reportData = <ClaimsLag>({
            serviceDate: r.serviceDate,
            totalPaidAmount: r.totalPaidAmount
        });

        return reportData;
    }

}