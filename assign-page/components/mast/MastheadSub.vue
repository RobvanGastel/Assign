<template>
	<div class="row component-masthead-sub">
    <section class="masthead-sub section-padding">
			<div class="container">
				<div class="row">

					<div class="col-xs-12 col-sm-5 pull-right">
						<img v-bind:src="'/illustratie/illustration-' + illustratie + '.svg'" v-bind:alt="'Illustratie van ' + illustratie" class="step-illustration" draggable="false">
					</div>

					<div class="col-xs-12 col-sm-7 col-lg-6 col-lg-offset-1 content-text">
            <h1>{{ title }}</h1>
            <h3>{{ description }}</h3>
						<a v-if="firstbtn" class="btn btn-primary" v-bind:title="firstbtn" v-bind:target="firstbtntrgt" v-bind:href="firstbtnlnk">{{ firstbtn }}</a>
						<a v-if="secondbtn" class="btn btn-primary" v-bind:title="secondbtn" v-bind:target="secondbtntrgt" v-bind:href="secondbtnlnk">{{ secondbtn }}</a>
          </div>

        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  props: ['title', 'description', 'illustratie', 'firstbtn', 'firstbtnlnk', 'firstbtntrgt', 'secondbtn', 'secondbtnlnk', 'secondbtntrgt']
}
</script>
