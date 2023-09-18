import { Cadastro } from './../interface/cadastro';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CadastroService {

  url = `http://localhost:8025/pi/v1`

  constructor(
    private http: HttpClient
  ) { }

  buscaPessoa(): Observable<Cadastro[]>{
    return this.http.get<Cadastro[]>(`${this.url}/pessoas`)
  }

  cadastroPessoa(pessoa: Cadastro): Observable<Cadastro>{
    return this.http.post<Cadastro>(`${this.url}/insert`, pessoa)
  }
}
