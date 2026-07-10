import { ChangeDetectionStrategy, Component } from '@angular/core';
import { AbstractControl, FormBuilder,FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { of } from 'rxjs';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
  imports :[ReactiveFormsModule]
})

export class LoginComponent {
  loginForm = new FormGroup({
    email : new FormControl<string>('',{
      validators: [Validators.email, Validators.required],
      asyncValidators: [emailIsUnique],
    }),
      password : new FormControl('',{
      validators: [Validators.minLength(6), Validators.required,questionMarkValidator],
    })
  });
  
  onSubmit() {
    console.log(this.loginForm);
    const enteredEmail = this.loginForm.value.email;
    const enteredPassword = this.loginForm.value.password;
    // console.log(enteredEmail, enteredPassword);
  }
  
  get emailValidation() {
    return(
      this.loginForm.controls.email.touched &&
      this.loginForm.controls.email.invalid &&
      this.loginForm.controls.email.dirty
    )
  }
  get passwordValidation(){
    return (
      this.loginForm.controls.password.touched &&
      this.loginForm.controls.password.invalid &&
      this.loginForm.controls.password.dirty 
    )
  }
}
function questionMarkValidator(controls:AbstractControl){
    if(controls.value.includes('?')){
      return null ;
    }   
    return {doesNotHaveQuestionMark : true};
}
function emailIsUnique(controls:AbstractControl){
    if(controls.value !== 'test@gmail.com'){
      return of(null) ;
    }   
    return of({emailIsNotUnique : true});
}