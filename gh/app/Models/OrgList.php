<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Notifications\Notifiable;

class OrgList extends Model{
    public $table = 'org_list';
    public $timestamps = false;
    public $fillable = ['id', 'hack_id', 'org_id'];
}
