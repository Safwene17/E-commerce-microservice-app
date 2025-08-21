import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { ShopLayoutComponent } from './layouts/shop-layout/shop-layout.component';
import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: "",
        component: ShopLayoutComponent,
        children:[]
    },
    {
        path:'admin',
        component: AdminLayoutComponent,
        children:[]
    }
];
