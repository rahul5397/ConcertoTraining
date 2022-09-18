import { Component } from '@angular/core';
import { Todo } from './model/todo';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'todoList';

  appTodoToAdd:any;
  newTodoAdded(todo1:Todo){
    console.log("from app component")
    console.log(todo1)
    this.appTodoToAdd=todo1;
  }
}
