<?php

namespace App\Http\Controllers\Api;

use App\Content;
use App\Hackathon;
use App\Http\Controllers\Controller;
use App\User;
use Illuminate\Http\Request;

class ContentController extends Controller
{
    public function find($id){
        return Content::find($id);
    }

    public function findByProject($id){
        return Content::where(['project_id' => $id])->orderBy('time', 'DESC')->get();
    }
}
