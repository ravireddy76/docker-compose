import { Component, OnInit } from '@angular/core';
import { ClaimExpenseService } from "../service/claimexpense.service";
import { Observable } from "rxjs/Observable";
import { Router } from "@angular/router";
import { jsPDF } from 'jspdf';
import autoTable from 'jspdf-autotable'
import * as XLSX from 'xlsx'; 

import html2canvas from 'html2canvas'; 

import { ClaimExpense } from '../models/claimsexpense.model';
import { ClaimExpenseSearch } from '../models/claimsexpensesearch.model';

@Component({
    selector: 'app-claimexpense',
    templateUrl: './claimexpense.component.html',
    styleUrls: ['./claimexpense.component.css'],
    providers: [ClaimExpenseService]
})
export class ClaimExpenseComponent implements OnInit {
    public claimExpenseSearch: ClaimExpenseSearch = new ClaimExpenseSearch();
    claimExpenseReportData: Observable<ClaimExpense[]>;

    constructor(private router: Router, private claimExpenseService: ClaimExpenseService) { }

    ngOnInit() { }

    /** Method to generate claim lag report by member group id and book of year. */
    public generateClaimExpenseReport() {
        console.log("Api Request MemberGroupId: " + this.claimExpenseSearch.memberGroupId + ", Service Year: " + this.claimExpenseSearch.bookYear);

        /** Trigger service api call. **/
        this.claimExpenseReportData = this.claimExpenseService.getClaimsExpense(this.claimExpenseSearch);
    }


    public exportPDF() {
        var data = document.getElementById('htmlReportData');  
        html2canvas(data).then(canvas => {  
            // Few necessary setting options  
            var imgWidth = 208;   
            var pageHeight = 295;    
            var imgHeight = canvas.height * imgWidth / canvas.width;  
            var heightLeft = imgHeight;  
            var pdfFileName = 'MemberGroupId_'+this.claimExpenseSearch.memberGroupId+'-ServiceYear_'+this.claimExpenseSearch.bookYear+".pdf";
  
            const contentDataURL = canvas.toDataURL('image/png')  
            let pdf = new jsPDF('p', 'mm', 'a4'); // A4 size page of PDF  
            var position = 12;  

            /* Header and Footer information. */ 
            var headerInformation = 'Claim Lag Report For ~ Member Group Id : '+this.claimExpenseSearch.memberGroupId+', Service Year : '+this.claimExpenseSearch.bookYear;
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
        var fileName = 'MemberGroupId_'+this.claimExpenseSearch.memberGroupId+'-ServiceYear_'+this.claimExpenseSearch.bookYear+".xlsx";
        
        /* table id is passed over here */   
        let data = document.getElementById('htmlReportData');  
        const ws: XLSX.WorkSheet =XLSX.utils.table_to_sheet(data);

        /* generate workbook and add the worksheet */
        const wb: XLSX.WorkBook = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');

        /* save to file */
        XLSX.writeFile(wb, fileName);
    }

}