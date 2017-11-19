<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Notifications\Notifiable;

class Hackathon extends Model{
    public $timestamps = false;
    public $fillable = ['id', 'name', 'desc', 'wlogo', 'logo', 'date'];
}
