<section id="container">

    <div id="large_bg_img" class="clearfix">

        <div id="large_bg_img_overlay"></div>
        <img src="/meetformeal/res/styles/default/img/bg2.jpg" id="image" class="big" alt="" />

        <h1>Crï¿½er une annonce</h1>

    </div>

    <div class="wrapper">

        <div class="col-2 centered">

            <form id="announcement-form" action="announcementcreate" method="post">
                <p>

                    <label for="time-start"><strong><i class="icon-time"></i> Vos disponibilités ? </strong></label>
                    Le <input type="text" id="time-start" name="dispo-date"> à <input type="text" id="time-start" name="dispo-hour">

                </p>
                <p>
                    <label for="description"><strong><i class="icon-pencil"></i> Description de l'annonce :</strong></label>
                    <textarea id="description" name="description" cols="30" rows="10"></textarea>
                </p>
                <p>
                    <input type="submit" class="btn" value="Crï¿½er l'annonce">
                </p>
            </form>

        </div>

    </div>

</section>
