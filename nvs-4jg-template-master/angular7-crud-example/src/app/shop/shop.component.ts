import { Component, OnInit } from '@angular/core';
import {Product} from '../model/product.model';
import {ProductService} from '../core/product.service';
import {Item} from './item';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {
  temp: Item = new Item();
  cart: Item[] = [];
  ProductArray: Product[];
  constructor(private productservice: ProductService) { }

  ngOnInit() {
    this.productservice.getProducts().subscribe(data => {
      this.ProductArray = data.result;
    },
      error => {
      console.log('ERROR @ shopcomponent.ts Product Array Fetch');
      });
  }
  public addToCart(item: Product): void {

    this.temp.id = item.id;
    this.temp.produktName = item.name;
    this.temp.preis = item.price;
    this.temp.anzahl = 1;
    if (this.alreadyExists(item)) {
      for (const cartItem of this.cart) {
        if (cartItem.id === this.temp.id) {
          cartItem.anzahl++;
        }
      }
    } else {
      this.cart.push(this.temp);
    }

    sessionStorage.setItem('carts', JSON.stringify(this.cart));
    this.temp = new Item();
  }

  private alreadyExists(product: Product): boolean {

    for (const cartItem of this.cart) {
      if (cartItem.id === product.id) {
        return true;
      }
    }
    return false;
  }
}

