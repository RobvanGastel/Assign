<template>
	<div class="row component-banner tanned-bg hidden-xs">

		<section class="banner">
			<div class="container">
				<div class="row">

          <div class="col-xs-12 col-md-9 col-lg-7 col-lg-offset-1">
            <h2>Maak jouw school communicatiever!</h2>
            <p>Vraag binnen enkele seconden een demo aan en begin met het creëren van een communicatieve leeromgeving.</p>
          </div>

					<div class="col-xs-12 col-md-3 pull-right">
						<button class="btn btn-primary" title="Vraag een demo versie aan"  @click="showModal = true">Probeer de demo</button>
						{{ showModal }}
					</div>

        </div>
      </div>
    </section>

  </div>
</template>

<script>
export default {
  data () {
    return {
      showModal: false
    }
  },
  props: ['showModal']
}
</script>
