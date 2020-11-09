import { Component, OnInit } from '@angular/core';
import { MemberService } from "../service/member.service";
import { Observable } from "rxjs/Observable";
import { Router } from "@angular/router";
import { jsPDF } from 'jspdf';
import autoTable from 'jspdf-autotable'
import * as XLSX from 'xlsx';

import html2canvas from 'html2canvas';

import { Member } from '../models/member.model';
import { MemberSearch } from '../models/membersearch.model';

@Component({
    selector: 'app-member',
    templateUrl: './member.component.html',
    styleUrls: ['./member.component.css'],
    providers: [MemberService]
})
export class MemberComponent implements OnInit {
    public memberSearch: MemberSearch = new MemberSearch();
    memberReportData: Observable<Member[]>;

    constructor(private router: Router, private memberService: MemberService) { }

    ngOnInit() { }

    /** Method to generate member subscribers report by member group id and/or benefit type. */
    public generateMemberSubscribersReport() {
        console.log("Api Request MemberGroupId: " + this.memberSearch.memberGroupId + ", Benefit Type: " + this.memberSearch.govtBenefitType);

        if (!this.memberSearch.memberGroupId) {
            this.memberSearch.memberGroupId = "";
        }
        if (!this.memberSearch.govtBenefitType) {
            this.memberSearch.govtBenefitType = "";
        }

        /** Trigger service api call. **/
        this.memberReportData = this.memberService.getMemberSubscribers(this.memberSearch);
    }


    public exportPDF() {
        var data = document.getElementById('htmlReportData');
        html2canvas(data).then(canvas => {
            // Few necessary setting options  
            var imgWidth = 208;
            var pageHeight = 295;
            var imgHeight = canvas.height * imgWidth / canvas.width;
            var heightLeft = imgHeight;
            var pdfFileName = 'MemberGroupId_' + this.memberSearch.memberGroupId + '-BenefitType_' + this.memberSearch.govtBenefitType + ".pdf";

            const contentDataURL = canvas.toDataURL('image/png')
            let pdf = new jsPDF('p', 'mm', 'a4'); // A4 size page of PDF  
            var position = 12;

            /* Header and Footer information. */
            var headerInformation = 'Member Subscribers Report For ~ Member Group Id : ' + this.memberSearch.memberGroupId + ', BenefitType : ' + this.memberSearch.govtBenefitType;
            pdf.setFont("helvetica");
            pdf.setFontSize(9);
            pdf.setTextColor("#800000");
            pdf.text(headerInformation, 0, 8);
            pdf.text("Copyrights to E&A team @2020", 10, 250);

            /* Page content information. */
            pdf.addImage(contentDataURL, 'PNG', 0, position, imgWidth, imgHeight);
            pdf.save(pdfFileName); // Generated PDF   
        });
    }

    public exportCSV(): void {
        //TODO Coming in future
    }

    public exportExcel(): void {
        var fileName = 'MemberGroupId_' + this.memberSearch.memberGroupId + '-BenefitType_' + this.memberSearch.govtBenefitType + ".xlsx";

        /* table id is passed over here */
        let data = document.getElementById('htmlReportData');
        const ws: XLSX.WorkSheet = XLSX.utils.table_to_sheet(data);

        /* generate workbook and add the worksheet */
        const wb: XLSX.WorkBook = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');

        /* save to file */
        XLSX.writeFile(wb, fileName);
    }
}