import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClaimLagComponent } from './claimlag/claimlag.component';
import { ClaimExpenseComponent } from './claimexpenses/claimexpense.component';
import { MemberComponent } from './membersubscription/member.component';

const routes: Routes = [
  { path:'claimlag', component: ClaimLagComponent},
  { path:'claimexpense', component: ClaimExpenseComponent},
  { path:'member', component: MemberComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
