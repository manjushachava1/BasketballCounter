import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.*
import kotlinx.android.synthetic.main.activity_save_scores.*
import java.util.*


private const val TAG = "GameListFragment"

class GameListFragment : Fragment(){

    interface Callbacks {
        fun onGameSelected(gameID: UUID)
        fun onDisplaySelected()
    }
    private var callbacks: Callbacks? = null

    private lateinit var gameRecyclerView: RecyclerView
    private var adapter: GameAdapter? = null
    private val gameListViewModel: GameListViewModel by lazy {
        ViewModelProviders.of(this).get(GameListViewModel::class.java)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
        Log.d("TAG", "onAttach() called")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_list, container, false)


        gameRecyclerView =
            view.findViewById(R.id.game_recycler_view) as RecyclerView
        gameRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()
        view.setBackgroundColor(Color.WHITE)

        return view
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
        Log.d("TAG", "onDetach() called")

    }
    private fun updateUI() {
        val games = gameListViewModel.games
        adapter = GameAdapter(games)
        gameRecyclerView.adapter = adapter
        Log.d("TAG", "updateUI() called")

    }

    private inner class GameHolder(view: View)
        : RecyclerView.ViewHolder(view), View.OnClickListener {

        val titleTextView: TextView = itemView.findViewById(R.id.game_title)
        val dateTextView: TextView = itemView.findViewById(R.id.game_date)
        val scoreATextView: TextView = itemView.findViewById(R.id.game_scoreA)
        val scoreBTextView: TextView = itemView.findViewById(R.id.game_scoreB)
        val teamATextView: TextView = itemView.findViewById(R.id.game_teamA)
        val teamBTextView: TextView = itemView.findViewById(R.id.game_teamB)
        val teamImageView: ImageView = itemView.findViewById(R.id.teamIcon)

        init {
            itemView.setOnClickListener(this)
        }

       private lateinit var game: Game

        fun bind(game: Game) {
            this.game = game
            titleTextView.text = game.title
            dateTextView.text = game.date.toString()
            scoreATextView.text = game.scoreA.toString()
            scoreBTextView.text = game.scoreB.toString()
            teamATextView.text = game.teamA
            teamBTextView.text = game.teamB
            if (game.scoreA > game.scoreB || game.scoreA == game.scoreB) {
                teamImageView.setImageResource(R.drawable.a_team_icon)
            } else {
                teamImageView.setImageResource(R.drawable.b_team_icon)
            }
        }

        // when game is pressed
        override fun onClick(v: View) {
            callbacks?.onGameSelected(game.id)
            v.setBackgroundColor(Color.WHITE)
        }
    }

    private inner class GameAdapter(var games: List<Game>)
        : RecyclerView.Adapter<GameHolder>() {

//        val gamesA = mutableListOf<Game>()
//        val gamesB = mutableListOf<Game>()
//
//        fun sortGames(){
//            for (element in games)
//                if(element.scoreA > element.scoreB || element.scoreA == element.scoreB)
//                    gamesA.add(element)
//                else if(element.scoreB > element.scoreA)
//                    gamesB.add(element)
//            Log.d("sortGames", gamesA.toString())
//        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : GameHolder {
            val layoutInflater = LayoutInflater.from(context)
            val view = layoutInflater.inflate(R.layout.list_item_game, parent, false)
            return GameHolder(view)
        }

        override fun getItemCount() = games.size

        override fun onBindViewHolder(holder: GameHolder, position: Int) {
            val game = games[position]
            holder.bind(game)
        }
    }

    companion object {
        fun newInstance(): GameListFragment {
            return GameListFragment()
        }
    }
}

