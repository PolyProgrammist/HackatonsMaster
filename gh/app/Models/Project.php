<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Notifications\Notifiable;

class Project extends Model{
    public $timestamps = false;
    public $fillable = ['id', 'name', 'desc', 'demo', 'hack_id', 'result_id'];
}
