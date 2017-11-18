<?php

namespace App\Http\Controllers\Api;

use App\Hackathon;
use App\Http\Controllers\Controller;
use App\User;
use Illuminate\Http\Request;

class HackathonsController extends Controller
{
    public function get(){
        return Hackathon::all();
    }

    public function find($id){
        return Hackathon::find($id);
    }
}
