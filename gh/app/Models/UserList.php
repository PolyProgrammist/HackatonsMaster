<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Notifications\Notifiable;

class UserList extends Model{
    public $table = 'user_list';
    public $timestamps = false;
    public $fillable = ['id', 'user_id', 'project_id'];
}
