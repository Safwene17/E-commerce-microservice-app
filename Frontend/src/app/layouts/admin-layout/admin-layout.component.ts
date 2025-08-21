import { Component } from '@angular/core';
import { AdminNavbarComponent } from "./admin-navbar/admin-navbar.component";
import { AdminFooterComponent } from "./admin-footer/admin-footer.component";

@Component({
  selector: 'app-admin-layout',
  standalone: true,
  imports: [AdminNavbarComponent, AdminFooterComponent],
  templateUrl: './admin-layout.component.html',
  styleUrl: './admin-layout.component.scss'
})
export class AdminLayoutComponent {

}
