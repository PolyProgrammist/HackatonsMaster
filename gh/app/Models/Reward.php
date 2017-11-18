<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Notifications\Notifiable;

class Reward extends Model{
    public $timestamps = false;
    public $fillable = ['id', 'name', 'prize', 'priority', 'hack_id'];
}
