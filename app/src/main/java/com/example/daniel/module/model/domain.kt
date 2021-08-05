package com.example.daniel.myapplication.com.example.daniel.module.model

data class QueryRes(val Status:String, val TC:Boolean,val AD:Boolean,val CD:Boolean,
                    val RD:Boolean,val RA:Boolean,val Question:Question, val Answer:List<Answer>)

data class Question(val name:String, val type:Int)

data class Answer(val name:String,val TTL:Int,val type:Int,val data:String)