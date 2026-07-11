import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder,FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { debounce, debounceTime, of } from 'rxjs';

//$P new way of binding data to form first take the and then declare it as a inital value in respective form control.
const emailInitialValue  = localStorage.getItem('email');

function questionMarkValidator(controls:AbstractControl){
    if(controls.value.includes('?')){
      return null ;
    }   
    return {doesNotHaveQuestionMark : true};
}
function emailIsUnique(controls:AbstractControl){
    if(controls.value !== 'test@gmail.com'){ // Dummy data mostly here we can query from backend to check if the email is unique or not.
      return of(null) ;
    }   
    return of({emailIsNotUnique : true});
}

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
  imports :[ReactiveFormsModule]
})


export class LoginComponent implements OnInit {
  loginForm = new FormGroup({
    email : new FormControl<string>(emailInitialValue || '',{
      validators: [Validators.email, Validators.required],
      asyncValidators: [emailIsUnique],
    }),
    password : new FormControl('',{
      validators: [Validators.minLength(6), Validators.required,questionMarkValidator],
    })
  });
  ngOnInit(): void {

    // $P setting inside ngOnInit() to is the old way of setting the value of the form control to a respective from group element.
    // let email = localStorage.getItem('email');
    // if(email){
    //   // this.loginForm.controls.email.setValue(email);                   // old way 
    //   // this.loginForm.setValue({email: email, password: ''});           // old way
    //   this.loginForm.patchValue({email: email});                          // new way
    // }

    this.loginForm.valueChanges.pipe(
      debounceTime(500)
    ).subscribe({
      next: (value) => {
        localStorage.setItem('email',this.loginForm.value.email||'');
      }
    });
  }
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
