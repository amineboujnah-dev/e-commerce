import {ItemProduct} from './item-product.model';
import {Client} from './client.model';

export class Caddy {
  constructor(name: string) {
    this.name = name;
  }
  public name: string;
  public items: Map<number, ItemProduct> = new Map(); // items est une liste(Map) qui contient number(id du produit)
  // et ItemProduct(quantité)
  public client: Client;
}
