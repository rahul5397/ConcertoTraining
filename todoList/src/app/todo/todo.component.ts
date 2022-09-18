import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Todo } from '../model/todo';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {

  @Input() todo:Todo;
  checked:boolean= false;
  constructor() {
    this.todo = {
      etodo:""
    }
   }

  ngOnInit(): void {
  }

  @Output()
  todocheckbox:EventEmitter<Todo>=new EventEmitter();
  checkbox(){
    console.log("checked")
    this.checked = true;
    console.log(this.checked)
    this.todo.etodo
    this.todocheckbox.emit(this.todo)
  }

}
