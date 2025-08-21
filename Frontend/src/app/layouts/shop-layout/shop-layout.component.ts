import { Component } from '@angular/core';
import { ShopNavbarComponent } from "./shop-navbar/shop-navbar.component";
import { ShopFooterComponent } from "./shop-footer/shop-footer.component";

@Component({
  selector: 'app-shop-layout',
  standalone: true,
  imports: [ShopNavbarComponent, ShopFooterComponent],
  templateUrl: './shop-layout.component.html',
  styleUrl: './shop-layout.component.scss'
})
export class ShopLayoutComponent {

}
