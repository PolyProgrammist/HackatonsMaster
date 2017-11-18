<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Notifications\Notifiable;

class Content extends Model{
    public $timestamps = false;
    public $fillable = ['id', 'text', 'photo', 'video', 'git', 'time', 'project_id'];
}
