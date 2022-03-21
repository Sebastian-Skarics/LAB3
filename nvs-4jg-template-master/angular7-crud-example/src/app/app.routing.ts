import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from './home/home.component';
import {ShopComponent} from './shop/shop.component';
import {CartComponent} from './cart/cart.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent},
  { path: 'shop', component: ShopComponent},
  { path: 'cart', component: CartComponent},
  {path : '', component : HomeComponent}
];

export const routing = RouterModule.forRoot(routes);
