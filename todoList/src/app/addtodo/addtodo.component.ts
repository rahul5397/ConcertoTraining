import { Component, OnInit, Output } from '@angular/core';
import { EventEmitter } from '@angular/core';
import { Todo } from '../model/todo';
import { TodohttpService } from '../service/todohttp.service';

@Component({
  selector: 'app-addtodo',
  templateUrl: './addtodo.component.html',
  styleUrls: ['./addtodo.component.css']
})
export class AddtodoComponent implements OnInit {
  
  constructor(private todoservice: TodohttpService) { }
  

  ngOnInit(): void {
  }

  @Output()
  todoAdded:EventEmitter<Todo> = new EventEmitter();

  addToDo(todo:Todo){
    this.todoAdded.emit(todo)
    console.log("hello")
    console.log(todo)
    this.todoservice.addTodo(todo).subscribe();
  }
}
