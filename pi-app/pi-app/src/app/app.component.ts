import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CadastroService } from './service/cadastro.service';
import { Subject, takeUntil } from 'rxjs';
import { Cadastro } from './interface/cadastro';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy{


  title = 'pi-app';
  usuario!: FormGroup;
  isVisible = false;
  pessoas!: Cadastro[];
  private readonly destroy$: Subject<void> = new Subject();

  constructor(
    private service: CadastroService,
    private formBuilder: FormBuilder,
  ) {}


  ngOnInit(): void {
    this.service.buscaPessoa()
    .pipe(takeUntil(this.destroy$))
    .subscribe({
      next: (data) => {
          data && (this.pessoas = data);
      },
      error: (error) => console.log(error)

    })

    this.usuario = this.formBuilder.group({
      nome: ['', Validators.required],
      profissao: ['', Validators.required],
      idade: ['', Validators.required],
      descricao: ['', Validators.required],
      telefone: ['', Validators.required],
      email: ['', Validators.required]
    })

  }

  cadastrarPessoa(): void{
    if(this.usuario.valid){
      this.service.cadastroPessoa(this.usuario.value)
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          error: (error) => alert('Erro ao cadastrar!' + error),
          complete: () => {
            alert('Sucesso ao cadastrar!'),
            this.usuario.reset(),
            this.ngOnInit();
          }
        })
    }
    else{
      alert('Preencha o Formulário!')
    }
  }


  showModal(): void {
    this.isVisible = true;
  }

  handleOk(): void {
    console.log('Button ok clicked!');
    this.isVisible = false;
  }

  handleCancel(): void {
    console.log('Button cancel clicked!');
    this.isVisible = false;
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

}
