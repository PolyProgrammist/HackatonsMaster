<?php

namespace App\Http\Controllers\Api;

use App\Hackathon;
use App\Http\Controllers\Controller;
use App\Reward;
use App\User;
use Illuminate\Http\Request;

class RewardsController extends Controller
{
//    public function get(){
//        return Reward::all();
//    }

    public function find($id){
        return Reward::find($id);
    }
}
