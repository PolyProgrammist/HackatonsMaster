<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateProjects extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
//        public $fillable = ['id', 'name', 'desc', 'demo', 'hack_id', 'result_id'];
        Schema::create('projects', function (Blueprint $table) {
            $table->increments('id');
            $table->string('git');
            $table->string('name');
            $table->string('desc');
            $table->string('demo');
            $table->integer('hack_id');
            $table->integer('result_id');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('projects');
    }
}