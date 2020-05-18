import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddTestComponent } from './add-test/add-test.component';
import { AddCenterComponent } from './add-center/add-center.component';
import { RemoveTestComponent } from './remove-test/remove-test.component';
import { RemoveCenterComponent } from './remove-center/remove-center.component';
import { ViewCenterComponent } from './view-center/view-center.component';


const routes: Routes = [
  {path:'addtest', component:AddTestComponent},
  {path:'addcenter', component:AddCenterComponent},
  {path:'removetest',component:RemoveTestComponent},
  {path:'removecenter',component:RemoveCenterComponent},
  {path:'veiwcenter',component:ViewCenterComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
