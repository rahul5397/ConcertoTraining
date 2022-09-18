import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Todo } from '../model/todo';

@Injectable({
  providedIn: 'root'
})
export class TodohttpService {
  url:string = "http://localhost:3000/todos"; 

  constructor(private http:HttpClient) { }


  getAllTodos():Observable<Todo[]>{
    console.log("get todos from service class")
    return this.http.get<Todo[]>(this.url);
  }

  addTodo(todo:Todo):Observable<Todo>{
    console.log("end in service class")
    return this.http.post<Todo>(this.url, todo);
  }

  delTodo(id:number){
    console.log("deleted from service class")
    return this.http.delete(this.url+'/'+id )
  }
  
  }
  

