import { Component } from '@angular/core';
import { AbstractControl, FormArray, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css',
})
export class SignupComponent {
  signUpForm = new FormGroup({
    email: new FormControl('', {
      validators: [Validators.required, Validators.email],
    }),
    passwordGrid: new FormGroup({
      password: new FormControl('', {
        validators: [Validators.required, Validators.minLength(6)]
      }),
      confirmPassword: new FormControl('', {
        validators: [Validators.required, Validators.minLength(6)]
      }),     
    } ,{
      validators:[passwordMatchValidator('password', 'confirmPassword')]
    }),
    role: new FormControl<'student' | 'teacher' | 'employee' | 'founder' | 'other'>('student', {
      validators: [Validators.required]
    }),
    source: new FormArray([
      new FormControl(false),
      new FormControl(false),
      new FormControl(false),
    ]),
  });

  onSubmit() {
    console.log(this.signUpForm);
  }

  onReset() {
    this.signUpForm.reset();
  }

}

function passwordMatchValidator(s1: string, s2: string) {
  return (control: AbstractControl) => {
    const password = control.get(s1)?.value;
    const confirmPassword = control.get(s2)?.value;
    if (password === confirmPassword) {
      return null;
    }
      return { passwordDontMatch: true };
  }
}