<template>
	<div class="row component-download">

		<section class="download">
			<div class="container">
				<div class="row">

          <div class="col-xs-12 col-md-8 col-md-offset-2 text-center">
            <h5>Begin anderen te helpen</h5>
            <h2>Download de app</h2>
            <p>Als jouw school een Assign licentie heeft kan jij de app downloaden in de App Store voor iPhone. Heeft jouw school geen licentie? Laat jouw school dit weten.</p>
            <nuxt-link class="btn btn-primary" alt="Vraag een demo versie aan" :to="'#demo'">Download de iOS App</nuxt-link>
						<nuxt-link class="btn btn-secundary hidden-xs" alt="Deel met jouw school" :to="'#delen'">Deel met jouw school</nuxt-link>
          </div>

        </div>
      </div>
    </section>

  </div>
</template>


<script>
import MastheadNavigation from '../components/MastheadNavigation.vue'
export default {
  components: {
    MastheadNavigation
  }
}
</script>
