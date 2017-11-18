<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Notifications\Notifiable;

class Org extends Model{
    public $timestamps = false;
    public $fillable = ['id', 'name'];
}
