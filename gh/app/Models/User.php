<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Notifications\Notifiable;

class User extends Model{
    public $timestamps = false;
    public $fillable = ['id', 'name', 'desc', 'git'];
}
