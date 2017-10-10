<template>
	<div class="row component-masthead-sub">
    <section class="masthead-sub section-padding">
			<div class="container">
				<div class="row">

					<div class="col-xs-12 col-sm-5 pull-right">
						<img src="../assets/img/illustration-contact.svg" alt="Assign niet gevonden" class="step-illustration" draggable="false">
					</div>

					<div class="col-xs-12 col-sm-7 col-md-6 col-md-offset-1 content-text">
            <h1>{{ title }}</h1>
            <h3>{{ description }}</h3>
						<a class="btn btn-primary" title="Twitter" target="_blank" href="https://twitter.com/assignappnl">Bekijk ons Twitter</a>
						<a class="btn btn-primary" title="Mail" href="mailto:assignapp%2edev%40gmail%2ecom">Mail ons</a>
          </div>

        </div>
      </div>
    </section>

  </div>
</template>

<script>
export default {
  props: ['title', 'description']
}
</script>
