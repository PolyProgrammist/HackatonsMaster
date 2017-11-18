<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateContents extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
//        public $fillable = ['id', 'text', 'photo', 'video', 'git', 'project_id', 'time'];
        Schema::create('contents', function (Blueprint $table) {
            $table->increments('id');
            $table->string('text');
            $table->string('photo');
            $table->string('video');
            $table->string('git');
            $table->dateTime('time');
            $table->integer('project_id');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('contents');
    }
}
