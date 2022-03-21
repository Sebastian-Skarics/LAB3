import { Component, OnInit } from '@angular/core';
import {Item} from '../shop/item';
import {Router} from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cart: Item[] = [];
  cartNew: Item[] = [];
  gesamtPreis = 0;

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.cart = JSON.parse(sessionStorage.getItem('carts'));
    if (!this.cart) {
      this.cart = [];
    }

    for (const element of this.cart) {
      this.gesamtPreis += (element.anzahl * element.preis);
    }
  }

  delete(id: Item): void {
    this.cart = JSON.parse(sessionStorage.getItem('carts'));
    let i = 0;

    while (i < this.cart.length) {
      if (this.cart[i].id !== id.id) {
        this.cartNew.push(this.cart[i]);
      }
      i++;
    }
    sessionStorage.setItem('carts', JSON.stringify(this.cartNew));
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate(['/cart']);
    });

  }

}
